/**
 * 
 */

"use strict";

(function () {
	
	function Start() {
		
		if(document.title == "Register"){
			RegisterPage();
		}
		
	}
	console.log("IFFE");
	window.addEventListener("load", Start);
	
})()

function RegisterPage(){
	console.log("regy function");
	
	
	doucument.querySelector("[name=inputId]").onchange( function(){
		let messageArea = document.querySelector("#idErr");
		let name_reg = /\d{9}/;
		let temp = document.querySelector(this).value;
		if(!name_reg.test(temp)){
			
			$(this).trigger("focus").trigger("select"); 
            messageArea.addClass("alert alert-danger").text("ID must 9 digits in length").show();
            
		}else{
			
			messageArea.removeAttr("class").hide();
		}
	});
	
	doucument.querySelector("[name=inputEmail]").onchange( function(){
		let messageArea = document.querySelector("#emailErr");
		let name_reg = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/;
		let temp = document.querySelector(this).value;
		if(!name_reg.test(temp)){
			
			$(this).trigger("focus").trigger("select"); 
            messageArea.addClass("alert alert-danger").text("Email is Bunk").show();
            
		}else{
			
			messageArea.removeAttr("class").hide();
		}
	});
	
	doucument.querySelector("[name=inputFirst]").onchange( function(){
		let messageArea = document.querySelector("#firstErr");
		let name_reg = /\b([A-Z]+[-,a-z.']+)/;
		let temp = document.querySelector(this).value;
		if(!name_reg.test(temp)){
			
			$(this).trigger("focus").trigger("select"); 
            messageArea.addClass("alert alert-danger").text("First Name Is Bunk").show();
            
		}else{
			
			messageArea.removeAttr("class").hide();
		}
	});
	
	doucument.querySelector("[name=inputLast]").onchange( function(){
		let messageArea = document.querySelector("#lastErr");
		let name_reg = /\b([A-Z]+[-,a-z.']+)/;
		let temp = document.querySelector(this).value;
		if(!name_reg.test(temp)){
			
			$(this).trigger("focus").trigger("select"); 
            messageArea.addClass("alert alert-danger").text("Last Name Is Bunk").show();
            
		}else{
			
			messageArea.removeAttr("class").hide();
		}
	});
}