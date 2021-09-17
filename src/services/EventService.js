import axios from 'axios'

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default {
    listByBreweryId(breweryId) {
        return http.get(`/breweries/${breweryId}/events`)
    },
    listUpComing() {
        return http.get(`/events`)
    },
    findById(id) {
        return http.get(`/events/${id}`)
    },
    create(id, event) {
        return http.post(`/events/${id}`, event)
    },
    delete(id) {
        return http.delete(`/events/${id}`)
    },
    update(id, event) {
        return http.put(`/events/${id}`, event)
    }

}