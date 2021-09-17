<template>
  <div>
    <div class="beer-summary">
      <div class="beer-menu-box" v-on:click.prevent="toggleHidden()">
        <div><img class="beerimg" :src="beer.beerImage" /></div>
        <a href="" class="beer-name-link"
          ><div class="beer-name">{{ beer.beerName }}</div></a
        >
        <div class="beer-style">{{ beer.beerStyle }}</div>
        <div class="beer-abv">{{ beer.beerAbv + "%" }}</div>
      </div>
      <beer-detail
        v-show="!isHidden"
        v-bind:beer="beer"
        v-bind:brewerId="brewerId"
      />
      <button
        class="btn"
        v-show="!isHidden"
        v-on:click.prevent="toggleHidden()"
      >
        Minimize
      </button>
    </div>
  </div>
</template>

<script>
import BeerDetail from "./BeerDetail.vue";
export default {
  components: { BeerDetail },
  data() {
    return {
      isHidden: true,
    };
  },
  props: {
    beer: Object,
    brewerId: Number,
  },
  methods: {
    toggleHidden() {
      this.isHidden = !this.isHidden;
    },
  },
};
</script>

<style >
</style>