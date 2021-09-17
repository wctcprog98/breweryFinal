<template>
  <div
    class="flex-container-card"
    @mouseover="showButtons = true"
    @mouseleave="showButtons = false"
  >
    <div>
      <h2>{{ brewery.breweryName }}</h2>
      <h2>{{ brewery.breweryCity + "," + brewery.breweryState }}</h2>
    </div>
    <div v-show="showButtons">
      <button v-on:click="viewDetails()" v-if="$store.state.token != ''">
        View Details
      </button>
      <button v-else v-on:click="scrollToTop()">Log in View Details</button>
    </div>
  </div>
</template>


<script>
export default {
  props: {
    brewery: Object,
  },
  data() {
    return {
      showButtons: false,
    };
  },
  methods: {
    viewDetails() {
      const id = this.brewery.id;
      this.$router.push({ name: "breweryDetails", params: { id } });
    },

    scrollToTop() {
      window.scrollTo(0, 0);
    },
  },
};
</script>

<style>
</style>