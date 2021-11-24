<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <form method="post" enctype="multipart/form-data" class="row row-cols-lg-auto g-3 align-items-center mb-5">
        <div class="col-12">
            <input type="text" class="form-control" name="catName" placeholder="Введите имя питомца" required>
        </div>
        <div class="col-12">
            <div class="input-group">
                <label class="input-group-text" for="inputGroupFile01">Фото</label>
                <input type="file" class="form-control" id="inputGroupFile01" name="file" required>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Добавить</button>
        </div>
    </form>

    <div class="mb-3">
        Ваши питомцы:
    </div>
    <div class="card-group">
        <#list cats as cat>
            <div class="col mb-2">
                <div class="card" style="width: 10rem;">
                    <img src="/uploads/${cat.filename}" class="card-img-top" alt="">
                    <div class="card-body">
                        <h5 class="card-title">${cat.name}</h5>
                    </div>
                </div>
            </div>
        <#else>
            Пока ни одного
        </#list>
    </div>
</@c.page>