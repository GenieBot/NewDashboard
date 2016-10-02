<#import "auth_layout.ftl" as auth>
<@auth.layout
title="Login"
form_text="Don't have an account? <a href=\"/register\">Register a new account!</a>"
form_url="/login"
show_forgot_password=true
>
	<label class="label">Email</label>
	<p class="control">
	    <input class="input" type="text" placeholder="Your email address" name="email">
	</p>
	<label class="label">Password</label>
	<p class="control">
	    <input class="input" type="password" placeholder="Your password" name="password">
	</p>
	<p class="control">
	    <button class="button is-primary is-fullwidth" type="submit">Login</button>
	</p>
</@auth.layout>