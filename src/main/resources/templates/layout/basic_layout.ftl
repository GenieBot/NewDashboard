<#macro layout
title="none"
>
	<#import "nav_layout.ftl" as nav>
    <@nav.layout title>
	    <section class="section" style="background-color: #f5f7fa;">
	        <div class="container">
		        <#if alert?has_content>
                    <div id="alert" class="notification is-warning" style="margin-bottom: 30px;">
                        <button id="alert-remove" class="delete"></button>
			        ${alert}
                    </div>
		        </#if>
			    <#nested />
	        </div>
	    </section>
    </@nav.layout>
</#macro>