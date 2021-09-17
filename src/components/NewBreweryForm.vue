<template>
  <div>
    <button v-on:click="showForm = !showForm">Add a New Brewery</button>

    <form
      class="brew-form"
      v-show="showForm"
      v-on:submit.prevent="addBrewery()"
    >
      <label for="brewery-name">Brewery Name</label>
      <input type="text" name="brewery-name" v-model="newBrewery.breweryName" />

      <label for="brewery-address"> Address</label>
      <input
        v-model="newBrewery.breweryStreetAddress"
        type="text"
        name="brewery-address"
      />

      <label for="brewery-city"> City</label>
      <input v-model="newBrewery.breweryCity" type="text" name="brewery-city" />

      <label for="brewery-state"> State</label>
      <input
        v-model="newBrewery.breweryState"
        type="text"
        name="brewery-state"
      />

      <label for="brewery-zip">Zip</label>
      <input
        v-model="newBrewery.breweryZipCode"
        type="text"
        name="brewery-zip"
      />

      <label for="brewery-website">Website</label>
      <input
        v-model="newBrewery.breweryWebsite"
        type="text"
        name="brewery-website"
      />
      <button type="submit" class="btn save">Save Brewery</button>
    </form>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  data() {
    return {
      showForm: false,
      newBrewery: {
        brewerId: this.$store.state.user.id,
      },
    };
  },
  methods: {
    addBrewery() {
      breweryService.create(this.newBrewery).then((response) => {
        if (response.status === 201) {
          this.showForm = false;
          this.newBrewery = {};
        }
      });
    },
  },
};
</script>

<style>
form {
  display: flex;
  flex-direction: column;
  width: 33%;
}
.brew-form {
  margin-left: 40pxpx;
  border: 2px solid black;
  border-radius: 15px;
  background-color: rgb(247, 221, 104);
  font-weight: bolder;
  font-size: 17px;
  padding: 1%;
  padding-left: 3%;
  border-bottom: 6px solid black;
  border-right: 6px solid black;
  border-top: 0px;
  border-left: 0px;
  filter: blur(0px);
  opacity: 90%;
  width: 500px;
}
</style>