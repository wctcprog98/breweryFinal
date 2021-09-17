<template>
  <div class="Header">
    <div class="flex-container">
      <router-link v-bind:to="{ name: 'home' }">
        <div>
          <img src="../images/logo.png" />
        </div>
      </router-link>
      <div class="title">Brewery Finder</div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style>
</style>