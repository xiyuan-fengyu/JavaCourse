<#assign body>
    <div class="container">
        <h1>Welcome, ${user}!</h1>
    </div>
</#assign>

<#assign script>
<script>
    console.log(ctx);
</script>
</#assign>

<#include "common.ftl">