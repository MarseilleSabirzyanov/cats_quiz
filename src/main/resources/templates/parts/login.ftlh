<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <div class="form-group mb-3 row">
            <label class="col-sm-2 col-form-label">Имя :</label>
            <div class="col-sm-4">
                <input  type="text" name="username"
                        class="form-control ${(usernameError?? || loginError??)?string('is-invalid', '')} ${(registrationSuccess??)?string('is-valid', '')}"
                        placeholder="Имя" required/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
                <#if loginError??>
                    <div class="invalid-feedback">
                        ${loginError}
                    </div>
                </#if>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Пароль:</label>
            <div class="col-sm-4">
                <input type="password" name="password"
                       class="form-control ${(passwordError?? || loginError??)?string('is-invalid', '')}"
                       placeholder="Пароль" required/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
                <#if loginError??>
                    <div class="invalid-feedback">
                        ${loginError}
                    </div>
                </#if>
            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit"><#if isRegisterForm>Зарегестрироваться<#else>Вход</#if></button>
    </form>
</#macro>

<#macro logout>
    <form action="/logout" method="post">
        <input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button class="btn btn-primary" type="submit">Выход</button>
    </form>
</#macro>