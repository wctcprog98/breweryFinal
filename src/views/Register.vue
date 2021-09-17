<template>
  <div>
    <registration-header class="reg-header" />
    <div id="register" class="text-center">
      <form class="form-register" @submit.prevent="register">
        <h1 class="h3 mb-3 font-weight-normal">Create Account</h1>
        <div class="alert alert-danger" role="alert" v-if="registrationErrors">
          {{ registrationErrorMsg }}
        </div>
        <label for="username" class="sr-only">Username</label>
        <input
          type="text"
          id="username"
          class="form-control"
          placeholder="Username"
          v-model="user.username"
          required
          autofocus
        />
        <label for="password" class="sr-only">Password</label>
        <input
          type="password"
          id="password"
          class="form-control"
          placeholder="Password"
          v-model="user.password"
          required
        />
        <input
          type="password"
          id="confirmPassword"
          class="form-control"
          placeholder="Confirm Password"
          v-model="user.confirmPassword"
          required
        />
        <label for="account-type">Account Type</label>
        <select
          name="account-type"
          class="form-control"
          v-model="user.accountType"
        >
          <option value="">--Please select a role</option>
          <option value="Beer Lover">Beer Lover</option>
          <option value="Brewer">Brewer</option>
          <option value="Administrator">Administrator</option>
        </select>
        <label for="user-location">Location:</label>
        <input name="user-location" type="text" v-model="user.userLocation" />
        <router-link :to="{ name: 'home' }">Have an account?</router-link>
        <button class="btn btn-lg btn-primary btn-block" type="submit">
          Create Account
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import RegistrationHeader from "../components/RegistrationHeader.vue";
import authService from "../services/AuthService";

export default {
  components: { RegistrationHeader },
  name: "register",
  data() {
    return {
      user: {
        username: "",
        password: "",
        confirmPassword: "",
        role: "user",
        accountType: "",
        userLocation: "",
      },
      registrationErrors: false,
      registrationErrorMsg: "There were problems registering this user.",
    };
  },
  methods: {
    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = "Password & Confirm Password do not match.";
      } else {
        if (
          this.user.accountType === "Administrator" ||
          this.user.accountType === "Brewer"
        ) {
          this.user.role = "admin";
        }

        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: "/",
                query: { registration: "success" },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = "Bad Request: Validation Errors";
            }
          });
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = "There were problems registering this user.";
    },
  },
};
</script>

<style>
</style>
