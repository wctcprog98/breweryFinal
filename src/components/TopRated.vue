<template>
  <div>
    <h1 class="rated-title">  Top Rated Beers :</h1>
  <div class="TopRated">
    

    <top-rated-summary
      class="event-item"
      v-for="beer in beers"
      :key="beer.id"
      :beer="beer"
    />
  </div>
</div>
</template>

<script>
import beerService from "@/services/BeerService";
import TopRatedSummary from "./TopRatedSummary.vue";

export default {
  components: { TopRatedSummary },
  data() {
    return {
      beers: [],
    };
  },
  created() {
    beerService.listTopRated().then((response) => {
      if (response.status === 200) {
        this.beers = response.data;
      }
    });
  },
};
</script>

<style>
.rated-title {
  color: white;
  border: 0px solid white;
  background-color:  rgb(92, 76, 43);
  margin-right: 68%;
  border-radius: 20px;
  justify-content: center;
  padding: 1%;
  
}
</style>