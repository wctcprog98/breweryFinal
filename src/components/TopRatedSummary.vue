<template>
  <div>
    <p>{{ beer.beerName }}</p>
    <p>{{ beer.beerStyle }}</p>
    <p>{{ beer.beerAbv }} % ABV</p>
    <p>{{ getBreweryName(beer.breweryId) }} {{ breweryName }}</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      breweryName: "",
    };
  },
  props: {
    beer: Object,
  },
  methods: {
    getBreweryName(breweryId) {
      let breweries = this.$store.state.breweries;

      breweries.forEach((brewery) => {
        if (brewery.id == breweryId) {
          this.breweryName = brewery.breweryName;
        }
      });
    },
  },
};
</script>

<style>
</style>