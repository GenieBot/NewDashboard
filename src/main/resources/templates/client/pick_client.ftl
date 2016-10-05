<#macro card
name="none"
link="#"
>
<a href="/clients/add/${link}">
    <div class="box">
        <p>
            ${name}
            <a href="/clients/add/${link}" class="button is-pulled-right" style="margin-top: -5px">Add ${name} Chat</a>
        </p>
    </div>
</a>
</#macro>

<#import "../layout/page_layout.ftl" as page>
<@page.layout "Pick Platform" "Pick Platform">
	<div class="columns">
		<div class="column is-6 is-offset-3">
			<#list clients as client>
				<#assign id = client["id"]>
				<#assign name = client["name"]>
				<@card name id />
			</#list>
        </div>
    </div>
</@page.layout>