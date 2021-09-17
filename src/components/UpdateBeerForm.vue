<template>
  <div>
    <button v-on:click="showForm = !showForm">Update A Beer</button>

    <form v-show="showForm" v-on:submit.prevent="updateBeer()">
      <label for="beer-name">beer Name</label>
      <input type="text" name="beer-name" v-model="newBeer.beerName" />

      <label for="brewer-id">beer ID</label>
      <input
        v-model="newBeer.brewerId"
        type="text"
        placeholder="do I work?"
        name="brewer-id"
      />

      <label for="beer-address"> Address</label>
      <input
        v-model="newBeer.beerStreetAddress"
        type="text"
        name="beer-address"
      />

      <label for="beer-city"> City</label>
      <input v-model="newBeer.beerCity" type="text" name="beer-city" />

      <label for="beer-state"> State</label>
      <input v-model="newBeer.beerState" type="text" name="beer-state" />

      <label for="beer-zip">Zip</label>
      <input v-model="newBeer.beerZipCode" type="text" name="beer-zip" />

      <label for="beer-website">Website</label>
      <input v-model="newBeer.beerWebsite" type="text" name="beer-website" />
      <button type="submit" class="btn save">Update</button>
    </form>
  </div>
</template>

<script>
import BeerService from "@/services/BeerService";
export default {
  data() {
    return {
      showForm: false,

      newBeer: {},
    };
  },
  methods: {
    updateBeer() {
      BeerService.update(this.newBeer).then((response) => {
        if (response.status === 201) {
          this.showForm = false;
          this.newBeer = {};
        }
      });
    },
  },
};
</script>

<style>
</style>