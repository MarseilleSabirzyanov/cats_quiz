<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="container">
        <div class="row justify-content-md-center">
            <div class="col col-lg-3">
                <div>
                    Ваш ТОП 10
                </div>
                <#list userRating as cat>
                    <div class="row mb-1">
                        <div class="card" style="width: 10rem;">
                            <img src="/uploads/${cat.filename}" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title">${cat.name}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">Рейтинг: ${cat.rating}</h6>
                            </div>
                        </div>
                    </div>
                <#else>
                    Вы еще не прошли викторину
                </#list>
            </div>
            <div class="col col-lg-3">
                <div>
                    Общий ТОП 10
                </div>
                <#list totalRating as cat>
                    <div class="row mb-1">
                        <div class="card" style="width: 10rem;">
                            <img src="/uploads/${cat.filename}" class="card-img-top" alt="">
                            <div class="card-body">
                                <h5 class="card-title">${cat.name}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">Рейтинг: ${cat.rating}</h6>
                            </div>
                        </div>
                    </div>
                <#else>
                    Еще никто не проходил викторину, будьте первым
                </#list>
            </div>
        </div>
    </div>
</@c.page>