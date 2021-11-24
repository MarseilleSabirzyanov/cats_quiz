<#include "security.ftlh">
<#import "login.ftlh" as l>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Котики</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <#if user??>
                    <li class="nav-item">
                        <a class="nav-link" href="/top">Топ котиков</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/quiz">Викторина</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/profile">Профиль</a>
                    </li>
                </#if>
            </ul>
            <#if user??>
                <div class="navbar-text me-3">${name}</div>
            </#if>
            <#if user??>
                <@l.logout />
            </#if>
        </div>
    </div>
</nav>