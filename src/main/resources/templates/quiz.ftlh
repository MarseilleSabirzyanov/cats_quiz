<#import "parts/common.ftlh" as c>

<@c.page>
    <form method="post">
        <div class="container">
            <div class="row justify-content-md-center">
                <div class="col col-lg-3">
                    <#if leftCat??>
                        <div class="row lg-3">
                            <button type="submit" class="btn btn-default" name="leftCat">
                                <img src="/uploads/${leftCat.filename}" width="250"/>
                            </button>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-link" name="leftCat">${leftCat.name}</button>
                        </div>
                        <input type="hidden" value="${leftCat.id}" name="leftCatId">
                    </#if>
                </div>
                <div class="col col-lg-3">
                    <#if rightCat??>
                        <div class="row lg-3">
                            <button type="submit" class="btn btn-default" name="rightCat">
                                <img src="/uploads/${rightCat.filename}" width="250"/>
                            </button>
                        </div>
                        <div class="row">
                            <button type="submit" class="btn btn-link" name="rightCat">${rightCat.name}</button>
                        </div>
                        <input type="hidden" value="${rightCat.id}" name="rightCatId">
                    </#if>
                </div>
            </div>
            <#if !leftCat??>
                <div class="row justify-content-start">
                    <div class="col-4">
                        <button type="submit" class="btn btn-primary" name="quiz" value="quiz">${startButton}</button>
                    </div>
                </div>
            </#if>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <#if pairId??>
                <input type="hidden" name="pairId" value="${pairId}"/>
            </#if>
        </div>
    </form>
</@c.page>