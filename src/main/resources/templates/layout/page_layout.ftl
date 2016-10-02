<#macro layout
title="none"
content_title="none"
>
	<#import "basic_layout.ftl" as basic>
    <@basic.layout title>
	    <#if alert?has_content>
	        <div id="alert" class="notification is-warning" style="margin-bottom: 30px;">
	            <button id="alert-remove" class="delete"></button>
	            ${alert}
	        </div>
	    </#if>
        <div style="text-align: center; text-transform: uppercase; margin-bottom: 30px;">
            <h1 class="title">${content_title}</h1>
        </div>
        <#nested />
    </@basic.layout>
</#macro>