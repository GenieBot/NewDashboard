<#macro card
name="none"
link="#"
>
<a href="/clients/add/${link}">
    <div class="card is-fullwidth">
        <div class="card-content">
            <div class="content">
                <#nested />
            </div>
        </div>
        <div class="card-content">
            <div class="content">
                <p>
                    ${name}
                    <a href="/chats/add" class="button is-pulled-right" style="margin-top: -5px">Add ${name} Chat</a>
                </p>
            </div>
        </div>
    </div>
</a>
</#macro>

<#macro icon_card
name="none"
link="#"
icon="none"
>
	<@card
	name=name
	link=link
	>
	    <p style="text-align: center; font-size: 120px; margin: 40px 0 30px 0;">
	        <i class="fa fa-${icon}"></i>
	    </p>
	</@card>
</#macro>

<#macro other_card
name="none"
link="#"
>
	<@card
	name=name
	link=link
	>
	    <#nested />
	</@card>
</#macro>


<#import "../layout/page_layout.ftl" as page>
<@page.layout "Pick Platform" "Pick Platform">
	<div class="columns">
	    <div class="column is-4">
			<@icon_card "Skype" "skype" "skype" />
	    </div>
	    <div class="column is-4">
		    <!-- redirect to oauth page -->
		    <@other_card "Discord" "discord">
		        <center>
                    <img src="https://discordapp.com/assets/41484d92c876f76b20c7f746221e8151.svg" alt="Discord logo"
                         style="margin: 32px 0 38px 0; height: 165px;" />
		        </center>
		    </@other_card>
	    </div>
	    <div class="column is-4">
		    <@icon_card "Slack" "slack" "slack" />
	    </div>
	</div>
</@page.layout>