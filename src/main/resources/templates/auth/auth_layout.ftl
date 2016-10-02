<#macro layout
title="none"
form_text="none"
form_url="#"
show_forgot_password=false
>
<#import "../layout/page_layout.ftl" as page>
<@page.layout title title>
<div class="columns">
    <div class="column is-6 is-offset-3">
        <div class="box">
            <p>${form_text}</p>
            <br />
            <form action="${form_url}" method="post">
                <#nested />
            </form>
            <br />
            <#if show_forgot_password>
		        <p>
	                <a href="/account/password">Forgot password</a>
	            </p>
            </#if>
        </div>
    </div>
</div>
</@page.layout>
</#macro>