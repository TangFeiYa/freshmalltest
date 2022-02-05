/**
 * 功能说明：全局配置
 */
var adminConfig = (function(env) {
	// 开发环境配置
	const DEV = {
		host : "http://manager.fresh.com:9101",
		apiServer : "http://manager.fresh.com:9101",
		name : "fresh-admin",
		description : "This is an admin project.",
		keywords : "admin,system,adminSystem",
		author : "caochenlei",
		theme : "skin-black-light",
		version : "1.0.0",
		copyright : "Copyright &copy; 2019-2021 <a>fresh-admin</a>.All rights reserved."
	};
	
	if (env == 'dev') return DEV;
}('dev'));
