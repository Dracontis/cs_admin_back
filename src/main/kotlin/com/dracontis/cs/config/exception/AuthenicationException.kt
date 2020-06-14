package com.dracontis.cs.config.exception

class AuthenicationException(errorCode: ErrorCode): ApplicationException(errorCode)