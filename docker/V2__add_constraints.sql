-- V2__add_constraints.sql
-- 1) создать sequence и default для book.id (если ещё нет)
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'book_id_seq') THEN
    CREATE SEQUENCE book_id_seq;
    PERFORM setval('book_id_seq', COALESCE((SELECT MAX(id) FROM public.book), 0));
    ALTER TABLE public.book ALTER COLUMN id SET DEFAULT nextval('book_id_seq');
  END IF;
END
$$;

-- 2) создать sequence и default для borrowed.id (если ещё нет)
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_class WHERE relname = 'borrowed_id_seq') THEN
    CREATE SEQUENCE borrowed_id_seq;
    PERFORM setval('borrowed_id_seq', COALESCE((SELECT MAX(id) FROM public.borrowed), 0));
    ALTER TABLE public.borrowed ALTER COLUMN id SET DEFAULT nextval('borrowed_id_seq');
  END IF;
END
$$;

-- 3) добавить UNIQUE на book.isbn (только если нет дублей)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_constraint WHERE conname = 'book_isbn_unique'
  ) THEN
    IF (SELECT COUNT(*) FROM (SELECT isbn FROM public.book GROUP BY isbn HAVING COUNT(*) > 1) AS t) = 0 THEN
      ALTER TABLE public.book ADD CONSTRAINT book_isbn_unique UNIQUE (isbn);
    ELSE
      RAISE NOTICE 'Cannot add UNIQUE(book.isbn): duplicates exist. Fix duplicates first.';
    END IF;
  END IF;
END
$$;

-- 4) добавить FK borrowed.isbn -> book.isbn (только если нет несвязанных записей)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_constraint WHERE conname = 'borrowed_book_fk'
  ) THEN
    IF (SELECT COUNT(*) FROM public.borrowed b LEFT JOIN public.book bk ON b.isbn = bk.isbn WHERE bk.isbn IS NULL) = 0 THEN
      ALTER TABLE public.borrowed ADD CONSTRAINT borrowed_book_fk FOREIGN KEY (isbn) REFERENCES public.book(isbn) ON DELETE CASCADE;
    ELSE
      RAISE NOTICE 'Cannot add FK borrowed->book: some borrowed.isbn have no matching book. Fix them first.';
    END IF;
  END IF;
END
$$;

-- 5) установить NOT NULL где безопасно (проверка на NULLs внутри)
DO $$
BEGIN
  IF (SELECT COUNT(*) FROM public.book WHERE isbn IS NULL) = 0 THEN
    ALTER TABLE public.book ALTER COLUMN isbn SET NOT NULL;
  ELSE
    RAISE NOTICE 'book.isbn contains NULLs — fix before setting NOT NULL';
  END IF;

  IF (SELECT COUNT(*) FROM public.book WHERE name IS NULL) = 0 THEN
    ALTER TABLE public.book ALTER COLUMN name SET NOT NULL;
  ELSE
    RAISE NOTICE 'book.name contains NULLs — fix before setting NOT NULL';
  END IF;

  IF (SELECT COUNT(*) FROM public.book WHERE author IS NULL) = 0 THEN
    ALTER TABLE public.book ALTER COLUMN author SET NOT NULL;
  ELSE
    RAISE NOTICE 'book.author contains NULLs — fix before setting NOT NULL';
  END IF;

  IF (SELECT COUNT(*) FROM public.borrowed WHERE isbn IS NULL) = 0 THEN
    ALTER TABLE public.borrowed ALTER COLUMN isbn SET NOT NULL;
  ELSE
    RAISE NOTICE 'borrowed.isbn contains NULLs — fix before setting NOT NULL';
  END IF;

  IF (SELECT COUNT(*) FROM public.borrowed WHERE user_id IS NULL) = 0 THEN
    ALTER TABLE public.borrowed ALTER COLUMN user_id SET NOT NULL;
  ELSE
    RAISE NOTICE 'borrowed.user_id contains NULLs — fix before setting NOT NULL';
  END IF;

  IF (SELECT COUNT(*) FROM public.borrowed WHERE due_date IS NULL) = 0 THEN
    ALTER TABLE public.borrowed ALTER COLUMN due_date SET NOT NULL;
  ELSE
    RAISE NOTICE 'borrowed.due_date contains NULLs — fix before setting NOT NULL';
  END IF;
END
$$;

