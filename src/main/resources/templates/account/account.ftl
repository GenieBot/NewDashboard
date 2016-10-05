<#import "../layout/page_layout.ftl" as page>
<@page.layout "Account" "Account">
<div class="columns">
    <div class="column is-half">
        <div class="box">
            <h3 class="title">Change email address</h3>
            <p>
                Current email: <code>${user_email}</code>
            </p>
            <br />
            <form action="/account/email" method="post">
                <label class="label">Your password</label>
                <p class="control">
                    <input class="input" type="password" name="password">
                </p>
                <label class="label">New email address</label>
                <p class="control">
                    <input class="input" type="text" name="email">
                </p>
                <label class="label">Confirm email address</label>
                <p class="control">
                    <input class="input" type="text" name="confirm_email">
                </p>
                <p class="control">
                    <button class="button" type="submit">Submit</button>
                </p>
            </form>
        </div>
    </div>
    <div class="column is-half">
        <div class="box">
            <h3 class="title">Change password</h3>
            <form action="/account/password" method="post">
                <label class="label">Current password</label>
                <p class="control">
                    <input class="input" type="password" name="current_password">
                </p>
                <label class="label">New password</label>
                <p class="control">
                    <input class="input" type="password" name="password">
                </p>
                <label class="label">Confirm password</label>
                <p class="control">
                    <input class="input" type="password" name="confirm_password">
                </p>
                <p class="control">
                    <button class="button" type="submit">Submit</button>
                </p>
            </form>
        </div>
    </div>
</div>
<div class="columns">
    <div class="column is-half">
        <div class="box">
            <h3 class="title">Delete account</h3>
            <p>
                Sorry to see you go! If you want to deactivate your account, please send an email to
                <code>support@geniebot.org</code> with the subject "Account deactivation" and your account
                email address.<br />Note, you will be asked to answer some questions to confirm your identity.
            </p>
        </div>
    </div>
    <div class="column is-half">
        <div class="box">
            <h3 class="title">Logout account</h3>
            <a href="/logout">
	            <button class="button is-large is-fullwidth is-danger">Logout</button>
            </a>
        </div>
    </div>
</div>
</@page.layout>