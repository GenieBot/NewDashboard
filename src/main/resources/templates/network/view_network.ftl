<#import "../layout/page_layout.ftl" as page>
<@page.layout "My Chat Name" "My Chat Name">
	<div class="columns">
		<div class="column is-3">
            <nav class="panel" style="background-color: white;">
                <p class="panel-heading" style="text-align: center">
                    Modules
                </p>
                <a class="panel-block" href="#">
				    <span class="panel-icon">
					    <i class="fa fa-check"></i>
				    </span>
                    Chatbot
                </a>
                <a class="panel-block" href="#">
				    <span class="panel-icon">
					    <i class="fa fa-times"></i>
				    </span>
                    Something else
                </a>
            </nav>
		</div>
		<div class="column is-6">
			<div class="box">
                <div style="text-align: center">
                    <h2 class="title">Module title</h2>
                    <h3 class="subtitle">Module subtitle</h3>
                </div>
				<br />
				<p>Memes man.</p>
			</div>
		</div>
		<div class="column is-3">
			<div class="box">
                <a class="button is-large is-danger is-fullwidth">Disable module</a>
			</div>
			<div class="box">
                <h3 class="title" style="text-align: center;">How to use</h3>
                <p>
	                Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus viverra enim non maximus tincidunt. Sed sed suscipit justo. Integer vestibulum nisi egestas varius laoreet.
                </p>
			</div>
		</div>
	</div>
</@page.layout>