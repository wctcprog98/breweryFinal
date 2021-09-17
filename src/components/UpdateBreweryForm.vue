<template>
  <div class="update-form">
    <form v-on:submit.prevent="updateBrewery()">
      <label for="brewery-name">Brewery Name</label>
      <input
        type="text"
        name="brewery-name"
        v-model="breweryToUpdate.breweryName"
      />

      <label for="brewer-id">BrewerID</label>
      <input
        type="text"
        placeholder="do I work?"
        name="brewer-id"
        v-model="breweryToUpdate.brewerId"
      />

      <label for="brewery-address"> Address</label>
      <input
        type="text"
        name="brewery-address"
        v-model="breweryToUpdate.breweryStreetAddress"
      />

      <label for="brewery-city"> City</label>
      <input
        type="text"
        name="brewery-city"
        v-model="breweryToUpdate.breweryCity"
      />

      <label for="brewery-state"> State</label>
      <input
        type="text"
        name="brewery-state"
        v-model="breweryToUpdate.breweryState"
      />

      <label for="brewery-zip">Zip</label>
      <input
        type="text"
        name="brewery-zip"
        v-model="breweryToUpdate.breweryZipCode"
      />

      <label for="brewery-website">Website </label>
      <input
        type="text"
        name="brewery-website"
        v-model="breweryToUpdate.breweryWebsite"
      />
      <button>Update Brewery</button>
    </form>
    <button v-on:click.prevent="cancel()">Cancel</button>
    <button v-on:click.prevent="deactivate()">Delete</button>
  </div>
</template>

<script>
import breweryService from "@/services/BreweryService";
export default {
  props: ["breweryNumber"],
  data() {
    return {
      breweryToUpdate: {},
    };
  },
  created() {
    breweryService.get(this.breweryNumber).then((response) => {
      this.breweryToUpdate = response.data;
    });
  },
  methods: {
    updateBrewery() {
      const brewery = {
        breweryName: this.breweryToUpdate.breweryName,
        brewerId: this.breweryToUpdate.brewerId,
        breweryStreetAddress: this.breweryToUpdate.breweryStreetAddress,
        breweryCity: this.breweryToUpdate.breweryCity,
        breweryState: this.breweryToUpdate.breweryState,
        breweryZipCode: this.breweryToUpdate.breweryZipCode,
        breweryWebsite: this.breweryToUpdate.breweryWebsite,
        active: this.breweryToUpdate.active,
      };
      breweryService.update(brewery, this.breweryNumber).then((response) => {
        if (response.status === 200) {
          this.$store.commit("TOGGLE_UPDATE_BREWERY");
        }
      });
    },
    cancel() {
      this.breweryToUpdate = {};
      this.$store.commit("TOGGLE_UPDATE_BREWERY");
    },
    deactivate() {
      if (confirm("Are you sure you want to permanently delete this brewery?"))
        breweryService.delete(this.breweryNumber).then((response) => {
          if (response.status === 200) {
            this.$router.push({ name: "home" });
          }
        });
    },
  },
};
</script>

<style>
.update-form {
  margin-left: 72px;
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