<template>
  <div>
    <button v-on:click="showForm = !showForm">Add a New Review</button>
    <form class="reviews-form" v-show="showForm">
      <label for="star-rating">Star Rating</label>
      <select name="star-rating" v-model="newReview.starRating">
        <option value="">--Please select a star rating</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
      </select>
      <label for="review-text">Leave us a Review!</label>
      <textarea name="review-text" v-model="newReview.reviewText" />
      <button typt="submit" v-on:click.prevent="postReview()">Submit</button>
      <button v-on:click.prevent="showForm = !showForm">Cancel</button>
    </form>
  </div>
</template>

<script>
import reviewService from "@/services/ReviewService";
export default {
  data() {
    return {
      showForm: false,
      newReview: {
        beerId: this.beerId,
        reviewerId: this.$store.state.user.id,
        reviewText: "",
        starRating: "",
      },
    };
  },
  props: {
    beerId: Number,
  },
  methods: {
    postReview() {
      this.newReview.starRating = parseInt(this.newReview.starRating);

      reviewService.create(this.newReview).then((response) => {
        if (response.status === 201) {
          this.newReview = {
            beerId: this.beerId,
            reviewerId: this.$store.state.user.id,
            reviewText: "",
            starRating: "",
          };
          this.$router.go();
        }
      });
    },
  },
};
</script>

<style>
.reviews-form{
  margin-left: 2px;
  border: 2px solid black;
  border-radius: 15px;
  background-color: rgb(247, 221, 104);
  font-weight: bolder;
  font-size: 17px;
  padding: 2%;
  padding-left: 3%;
  border-bottom: 6px solid black;
  border-right: 6px solid black;
  border-top: 0px;
  border-left: 0px;
  filter: blur(0px);
  opacity: 90%;
  width: 500px;
  color: black;
  height: 150px;
  justify-content: center;
}
</style>