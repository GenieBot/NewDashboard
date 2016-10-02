<#import "auth_layout.ftl" as auth>
<@auth.layout
title="Register"
form_text="Already have an account? <a href=\"/login\">Login!</a>"
form_url="/register"
>
	<label class="label">Email</label>
	<p class="control">
	    <input class="input" type="text" placeholder="Your email address" name="email">
	</p>
	<label class="label">Password</label>
	<p class="control">
	    <input class="input" type="password" placeholder="Your password" name="password">
	</p>
	<label class="label">Confirm password</label>
	<p class="control">
	    <input class="input" type="password" placeholder="Your password again" name="confirm_password">
	</p>
	<p class="control">
	    <button class="button is-primary is-fullwidth" type="submit">Register</button>
	</p>
</@auth.layout>