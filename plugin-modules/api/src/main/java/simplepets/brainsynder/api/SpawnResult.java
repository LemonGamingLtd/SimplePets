package simplepets.brainsynder.api;

public record SpawnResult<T>(State state, T value, String failMessage) {
    public static <T> SpawnResult<T> success(T value) {
        return new SpawnResult<>(State.SUCCESS, value, null);
    }
    public static <T> SpawnResult<T> fail(String message) {
        return new SpawnResult<>(State.FAILURE, null, message);
    }
    public static <T> SpawnResult<T> empty() {
        return new SpawnResult<>(State.EMPTY, null, null);
    }

    public boolean isSuccess() {
        return state == State.SUCCESS;
    }
    public boolean isFailure() {
        return state == State.FAILURE;
    }
    public boolean isEmpty() {
        return state == State.EMPTY;
    }

    public T value() {
        if (state != State.SUCCESS) throw new IllegalStateException("Missing spawn result value (state=" + state + ")");
        return value;
    }

    public String failMessage() {
        if (state != State.FAILURE) throw new IllegalStateException("Missing spawn attempt failure failMessage (state=" + state + ")");
        return failMessage;
    }

    public enum State {
        SUCCESS,
        FAILURE,
        EMPTY
    }
}
