package bassem.bm.task.nagwa.utils

sealed class Result<out T> {
  data class Success<T>(val value: T): Result<T>()
  data class Failure(val cause: Throwable): Result<Nothing>()
}

inline fun <T> Result(block: () -> T): Result<T> = try {
  Result.Success(block())
} catch (t: Throwable) {
  Result.Failure(t)
}


