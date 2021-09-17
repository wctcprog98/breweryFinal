<template>
  <div class="home">
    <div class="DashBoard">
      <Header />
      <events-list />
      <brewery-list />
      <top-rated />
    </div>
  </div>
</template>

<script>
import BreweryList from "../components/BreweryList.vue";
import EventsList from "../components/EventsList.vue";
import Header from "../components/Header.vue";
import TopRated from "../components/TopRated.vue";
export default {
  components: { Header, BreweryList, EventsList, TopRated },
  name: "home",
};
</script>

    