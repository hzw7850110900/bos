function conveterParamsToJson(paramsAndValues) {  
    var jsonObj = {};  
  
    var param = paramsAndValues.split("&");  
    for ( var i = 0; param != null && i < param.length; i++) {  
        var para = param[i].split("=");  
        jsonObj[para[0]] = para[1];  
    }  
  
    return jsonObj;  
}  
 
/**
 * 将表单数据封装为json
 * @param form
 * @returns
 */
function getFormData(form) {  
    var formValues = $("#" + form).serialize();  
    return conveterParamsToJson(decodeURIComponent(formValues));  
}  