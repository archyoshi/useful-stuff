/**
 * Functions that can be used in a salesforce Canvas App
 */

var salesforceREST;
if (!salesforceREST) {
	salesforceREST = {};
}

(function ($$) {

    "use strict";

    function onClickHandler() {
    }

    salesforceREST.userLookup = function(sr,callback) {
    	var url = sr.context.links.queryUrl+"?q=select+id+,+name+from+user+limit+10";
    	$$.client.ajax(url,
    		{client : sr.client,
    		method: "GET",
    		contentType: "application/json",
    		success: function(data){
    			if($$.isFunction(callback)) {
    				var returnedUsers = data.payload.records;
    				var optionStr = "";
    				for (var userPos = 0; userPos < returnedUsers.length; userPos = userPos + 1) {
    					optionStr = optionStr + '<option value="'
    					+ returnedUsers[userPos].Id + '">'
    					+ returnedUsers[userPos].Name + '</option>';
    				}
    				callback(optionStr);
    			}
    		}
    	});
    };
    
    salesforceREST.caseLookup = function(sr,callback,caseId) {
    	var url = sr.context.links.queryUrl+"?q=Select+Name,+t_Case__c.caseid__c,+Description__c,+InfoFromCanvas__c+from+t_Case__c+where+t_Case__c.caseid__c+=+'"+caseId+"'";
    	$$.client.ajax(url,
    		{client : sr.client,
    		method: "GET",
    		contentType: "application/json",
    		success: function(data){
    			if($$.isFunction(callback)) {
    				var returnedCases = data.payload.records;
    				var caseData = {
    						caseName:returnedCases[0].Name,
    						caseInfoFromCanvas:returnedCases[0].InfoFromCanvas__c,
    						caseDescription:returnedCases[0].Description__c};
    				callback(caseData);
    			}
    		}
    	});
    };
    
    salesforceREST.caseLookup2 = function(sr,callback) {
    	var url = sr.context.links.sobjectUrl+"t_Case__c/a00240000011O0s";
    	$$.client.ajax(url,
    		{client : sr.client,
    		method: "GET",
    		contentType: "application/json",
    		success: function(data){
    			if($$.isFunction(callback)) {
    				callback(data.payload);
    			}
    		}
    	});
    };
    
    salesforceREST.caseUpdate = function(sr,newDesc) {
    	var url = sr.context.links.sobjectUrl+"t_Case__c/a00240000011O0s";
    	var body = {"Description__c":newDesc};
    	$$.client.ajax(url,
    		{	
    			client : sr.client,
	    		method: "PATCH",
	    		contentType: "application/json",
	    		data: JSON.stringify(body),
	    		success: function(){
	    			alert("Record updated successfully.");
				},
	            error: function(){
	                alert("Error occurred updating local status.");
	            }
    		});
    };
    
    salesforceREST.caseCreate = function(sr,body) {
    	var url = sr.context.links.sobjectUrl+"t_Case__c";
    	$$.client.ajax(url,
    		{	
    			client : sr.client,
	    		method: "POST",
	    		contentType: "application/json",
	    		data: JSON.stringify(body),
	    		success: function(){
	    			alert("Record created successfully.");
				},
	            error: function(){
	                alert("Error occurred while creating case.");
	            }
    		});
    };

}(Sfdc.canvas));
