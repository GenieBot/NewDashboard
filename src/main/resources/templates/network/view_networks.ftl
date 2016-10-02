<#macro base_card
link="#"
>
<a href="${link}">
    <div class="card is-fullwidth">
        <#nested />
    </div>
</a>
</#macro>

<#macro chat_card
chat_name="none"
chat_id="none"
chat_image="http://placehold.it/300x225"
>
	<@base_card "/networks/${chat_id}">
	    <div class="card-image">
	        <figure class="image is-4by3">
	            <img src="${chat_image}" alt="${chat_name}">
	        </figure>
	    </div>
	    <div class="card-content">
	        <div class="content">
	            <span>${chat_name}</span>
	            <a href="/chats/${chat_id}" class="button is-pulled-right" style="margin-top: -5px;">Manage</a>
	        </div>
	    </div>
	</@base_card>
</#macro>

<#import "../layout/page_layout.ftl" as page>
<@page.layout "Chats" "Chats">
	<div>
        <form>
            <p class="control has-addons">
                <input class="input is-large is-expanded" type="text" placeholder="Find a chat">
                <a class="button is-large is-info">
                    Search
                </a>
            </p>
        </form>
		<p>
			<small>Can't see your chat? Use the search bar or <a href="/clients/add">add</a> a chat!</small>
		</p>
	</div>
	<br />
	<h3 class="subtitle">Skype chats</h3>
	<div class="columns">
	    <div class="column is-3">
			<@chat_card />
	    </div>
	    <div class="column is-3">
			<@chat_card />
	    </div>
	    <div class="column is-3">
			<@chat_card />
	    </div>
	</div>
	<h3 class="subtitle">Discord chats</h3>
	<div class="columns">
	    <div class="column is-3">
			<@chat_card />
	    </div>
	    <div class="column is-3">
			<@chat_card />
	    </div>
	    <div class="column is-3">
			<@chat_card />
	    </div>
        <div class="column is-3">
			<@chat_card />
        </div>
	</div>
	<h3 class="subtitle">Can't see your chat?</h3>
	<div class="columns">
	    <div class="column is-3">
			<@base_card "/clients/add">
                <div class="card-content">
                    <div class="content">
                        <p style="text-align: center; font-size: 113px; margin: 0px 0 5px 0;">&plus;</p>
                    </div>
                </div>
                <div class="card-content">
                    <div class="content">
                        <p>
                            <a href="/clients/add" class="button is-fullwidth" style="margin-bottom: -5px">
	                            Add Another Chat
                            </a>
                        </p>
                    </div>
                </div>
			</@base_card>
	    </div>
	</div>
</@page.layout>