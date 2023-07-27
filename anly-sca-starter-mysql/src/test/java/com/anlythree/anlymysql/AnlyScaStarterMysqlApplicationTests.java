package com.anlythree.anlymysql;


import org.apache.commons.lang3.StringUtils;

class AnlyScaStarterMysqlApplicationTests {


	public static void main(String[] args) {
		String a = "private static final Log logger = new Log(ZoneExceptionHandler.class);\u2028\u2028/**\u2028 * 参数校验异常\u2028 *\u2028 * @param e\u2028 * @return\u2028 */\u2028@ExceptionHandler(MethodArgumentNotValidException.class)\u2028@ResponseBody\u2028public ResBody handleParamException(MethodArgumentNotValidException e) {\u2028    //封装需要返回的错误信息\u2028    StringJoiner stringJoiner = new StringJoiner(\",\", \"参数异常【\", \"】\");\u2028    e.getBindingResult().getFieldErrors().forEach(fieldError -> stringJoiner.add(fieldError.getField() + \":\" + fieldError.getDefaultMessage()));\u2028    logger.error(stringJoiner.toString());\u2028    //错误返回\u2028    return ResBody.error(ResponseCodeEnum.PARAM_ERROR.getCode(), stringJoiner.toString());\u2028}\u2028\u2028\u2028/**\u2028 * zone自定义异常处理\u2028 *\u2028 * @param e\u2028 * @return\u2028 */\u2028@ExceptionHandler(ZoneException.class)\u2028@ResponseBody\u2028public ResBody handleException(ZoneException e) {\u2028    HttpServletRequest httpRequest = getHttpRequest();\u2028    httpRequest.getRequestURI();\u2028    logger.error(getTrace(), e, \"完整堆栈【\"+getStackTrace(e)+\"】\");\u2028    return ResBody.error(StringUtils.isBlank((e).getCode()) ? ResponseCodeEnum.SERVICE_FAILURE.getCode(): (e).getCode(),\u2028            StringUtils.isBlank((e).getMassage()) ? ResponseCodeEnum.SERVICE_FAILURE.getDesc() : (e).getMassage());\u2028}\u2028\u2028/**\u2028 * 兜底异常处理\u2028 *\u2028 * @param e\u2028 * @return\u2028 */\u2028@ExceptionHandler(Exception.class)\u2028@ResponseBody\u2028public ResBody handleException(Exception e) {\u2028    HttpServletRequest httpRequest = getHttpRequest();\u2028    httpRequest.getRequestURI();\u2028    logger.error(getTrace(), e, \"完整堆栈【\"+getStackTrace(e)+\"】\");\u2028    // 其他系统异常\u2028    return ResBody.error(ResponseCodeEnum.SERVICE_FAILURE);\u2028}\u2028\u2028\u2028protected HttpServletRequest getHttpRequest() {\u2028    ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();\u2028    return attributes.getRequest();\u2028}\u2028\u2028protected String getTrace() {\u2028    return this.getHttpRequest().getHeader(\"TRACE\");\u2028}\u2028\u2028/**\u2028 * 获取异常的堆栈信息\u2028 * @param throwable\u2028 * @return\u2028 */\u2028public static String getStackTrace(Throwable throwable)\u2028{\u2028    StringWriter sw = new StringWriter();\u2028    PrintWriter pw = new PrintWriter(sw);\u2028\u2028    try\u2028    {\u2028        throwable.printStackTrace(pw);\u2028        return sw.toString();\u2028    } finally\u2028    {\u2028        pw.close();\u2028    }\u2028}\n";
		String replace = StringUtils.replace(a, "\u2028", "\n");
		System.out.println(replace);
	}

}
