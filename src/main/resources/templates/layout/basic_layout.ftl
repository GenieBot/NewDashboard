<#macro layout
title="none"
>
	<#import "nav_layout.ftl" as nav>
    <@nav.layout title>
	    <section class="section" style="background-color: #f5f7fa;">
	        <div class="container">
			    <#nested />
	        </div>
	    </section>
    </@nav.layout>
</#macro>