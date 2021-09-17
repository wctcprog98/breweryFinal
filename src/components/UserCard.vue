<template>
  <div class="user-info">
    <div class="welcomeback">Welcome back,</div>
    <div>
      <p class="checkmark"> {{ $store.state.user.username }}! </p>
    </div>
    <div>
      <p>{{ $store.state.user.accountType }}</p>
    </div>

    <button v-on:click="logout()" class="logout-btn">Logout</button>
  </div>
</template>

<script>
export default {
  methods: {
    logout() {
      this.$store.commit("LOGOUT");

      if (this.$route.path != "/") {
        this.$router.push("/");
      }
    },
  },
};
</script>

<style>
.welcomeback{
  font-weight: bold;
  font-size: 20px;
}

.checkmark{
  height: 1px;
  width: 1px;
}

</style>