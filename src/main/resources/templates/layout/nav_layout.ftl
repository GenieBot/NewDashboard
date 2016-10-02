<#macro layout
title="none"
>
	<#import "base_layout.ftl" as base>
    <@base.layout title>
	    <div class="hero-head">
	        <div class="container">
	            <nav class="nav">
	                <div class="nav-left">
		                <span style="display: inline;">
                            <img src="/genie.jpg" style="height: 60px; margin-top: 10px;">
                        </span>
	                    <a class="nav-item is-active" href="/">
		                    <span style="font-size: 45px; letter-spacing: 4px; font-weight: bold">GENIE</span>
	                    </a>
	                </div>
					<span id="nav-toggle" class="nav-toggle" style="margin-top: 15px; margin-right: 15px;">
						<span></span>
						<span></span>
						<span></span>
					</span>
	                <div id="nav-menu" class="nav-right nav-menu" style="font-size: 25px; text-transform: uppercase;">
	                    <a class="nav-item" href="/networks">
	                        <span>Chats</span>
	                    </a>
	                    <a class="nav-item" href="/store">
	                        <span>Store</span>
	                    </a>
	                    <a class="nav-item" href="/login">
	                        <span>Login</span>
	                    </a>
	                </div>
	            </nav>
	        </div>
	    </div>
	    <#nested />
    </@base.layout>
</#macro>