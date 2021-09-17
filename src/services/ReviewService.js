import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    listByBreweryId(breweryId) {
        return http.get(`/breweries/${breweryId}/reviews`)
    },
    findById(id) {
        return http.get(`reviews/${id}`)
    },
    listByBeerId(beerId) {
        return http.get(`/beer/${beerId}/reviews`)
    },
    create(review) {
        return http.post(`/reviews`, review)
    },
    update(review, id) {
        return http.put(`/reviews/${id}`, review)
    },
    delete(id) {
        return http.delete(`/reviews/${id}`)
    }

}

