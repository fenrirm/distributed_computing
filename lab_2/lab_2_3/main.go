package main

import "fmt"

func getWinner(monks []int, start int, end int) int {
	index := start
	if monks[index] < monks[end] {
		index = end
	}
	return index
}

func getMon(id int) string {
	name := ""
	if id%2 == 0 {
		name = "Guan-in"
	} else {
		name = "Guan-yan"
	}
	return name
}

func fighting(chi []int, start int, end int, channel chan<- int) {
	winner := start
	if end-start < 2 {
		winner = getWinner(chi, start, end)
	} else {
		center := (start + end) / 2
		var channel1 = make(chan int, 2)
		go fighting(chi, start, center, channel1)
		fighting(chi, center, end, channel1)
		winner1 := <-channel1
		winner2 := <-channel1
		winner = getWinner(chi, winner1, winner2)
	}
	channel <- winner
}

func main() {
	chi := []int{15, 20, 40, 75, 100, 35, 50, 90, 60}
	var channel = make(chan int, 1)
	fighting(chi, 0, len(chi)-1, channel)
	var winner = <-channel
	fmt.Println("monk from monastery", getMon(winner), "won")
}
