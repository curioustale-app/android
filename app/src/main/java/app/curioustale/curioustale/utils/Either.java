package app.curioustale.curioustale.utils;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

public abstract class Either<L, R> {
    Either() {}

    public static <L, R> Either<L, R> left(L value) {
        return new Left<>(value);
    }

    public static <L, R> Either<L, R> right(R value) {
        return new Right<>(value);
    }

    public final boolean isRight() {
        return !isLeft();
    }

    public abstract boolean isLeft();
    public abstract Optional<L> getLeft();
    public abstract Optional<R> getRight();

    static class Left<L, R> extends Either<L, R> {
        private final L value;

        Left(L value) {
            this.value = requireNonNull(value);
        }

        @Override
        public Optional<L> getLeft() {
            return Optional.of(value);
        }

        @Override
        public boolean isLeft() {
            return true;
        }

        @Override
        public Optional<R> getRight() {
            return Optional.empty();
        }
    }

    static class Right<L, R> extends Either<L, R> {

        private final R value;

        Right(R value) {
            this.value = requireNonNull(value);
        }

        @Override
        public Optional<L> getLeft() {
            return Optional.empty();
        }

        @Override
        public boolean isLeft() {
            return false;
        }

        @Override
        public Optional<R> getRight() {
            return Optional.of(value);
        }
    }
}