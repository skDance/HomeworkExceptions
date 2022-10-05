package Exceptions

import java.lang.RuntimeException

class IncorrectReasonException(message: String) : RuntimeException(message)