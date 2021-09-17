<template>
  <div class="BreweryCard">
    <h1 class="brewery-label">Breweries:</h1>

    <div class="search-bar">
      <label for="" class="search-label">Name: </label>
      <input type="text" v-model="filter.breweryName" />
      <label for="" class="search-label">City: </label>
      <input type="text" v-model="filter.breweryCity" />
      <label for="" class="search-label">State: </label>
      <input type="text" v-model="filter.breweryState" />
    </div>

    <div style="overflow-y: scroll" class="brewery-card-scroll">
      <new-brewery-form
        v-if="
          $store.state.token != '' &&
          $store.state.user.accountType == 'Administrator'
        "
      />
      <brewery-summary
        class="card"
        v-for="brewery in filteredBreweries"
        v-bind:key="brewery.id"
        v-bind:brewery="brewery"
      />
    </div>
  </div>
</template>

<script>
import BrewerySummary from "@/components/BrewerySummary";
import BreweryService from "@/services/BreweryService";
import NewBreweryForm from "./NewBreweryForm.vue";
export default {
  components: { BrewerySummary, NewBreweryForm },

  data() {
    return {
      breweries: [],
      filter: {
        breweryName: "",
        breweryCity: "",
        breweryState: "",
      },
    };
  },
  created() {
    BreweryService.list().then((response) => {
      this.$store.commit("SET_BREWERIES", response.data);
      this.breweries = this.$store.state.breweries;
    });
  },
  computed: {
    filteredBreweries() {
      return this.breweries.filter((brewery) => {
        let nameMatch = brewery.breweryName
          .toLowerCase()
          .includes(this.filter.breweryName.toLowerCase());
        let cityMatch = brewery.breweryCity
          .toLowerCase()
          .includes(this.filter.breweryCity.toLowerCase());
        let stateMatch = brewery.breweryState
          .toLowerCase()
          .includes(this.filter.breweryState.toLowerCase());

        if (
          (!this.filter.breweryName || nameMatch) &&
          (!this.filter.breweryCity || cityMatch) &&
          (!this.filter.breweryState || stateMatch)
        ) {
          return brewery;
        }
      });
    },
  },
};
</script>

<style>
.search-label {
  color: white;
}

.search-bar {
  display: flex;
  justify-content: space-around;
  margin-bottom: 2%;
  border: 0px solid white;
  padding: 1.5%;
  background-color: rgb(153, 119, 8);
  border-radius: 20px;
}
</style>