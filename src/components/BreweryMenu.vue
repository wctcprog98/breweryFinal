<template>
  <div class="beer-menu">
    <h1 class="menu-title">Menu</h1>
    <new-beer-form
      v-show="$store.state.user.id == brewerId"
      v-bind:breweryNumber="breweryNumber"
    />
    <beer-summary
      v-for="beer in allBeers"
      v-bind:key="beer.id"
      v-bind:beer="beer"
      v-bind:brewerId="brewerId"
    />
  </div>
</template>

<script>
import BeerService from "@/services/BeerService";
import BeerSummary from "./BeerSummary.vue";
import NewBeerForm from "@/components/NewBeerForm.vue";

export default {
  components: { BeerSummary, NewBeerForm },
  props: {
    breweryNumber: Number,
    brewerId: Number,
  },
  data() {
    return {
      allBeers: [],
    };
  },
  created() {
    BeerService.listByBreweryId(this.breweryNumber).then((response) => {
      this.allBeers = response.data;
    });
  },
};
</script>

<style>
.menu-title {
  color: white;
}
</style>
