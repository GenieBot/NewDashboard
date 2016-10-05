<#macro layout
title="none"
content_title="none"
>
	<#import "basic_layout.ftl" as basic>
    <@basic.layout title>
        <div style="text-align: center; text-transform: uppercase; margin-bottom: 30px;">
            <h1 class="title">${content_title}</h1>
        </div>
        <#nested />
    </@basic.layout>
</#macro>