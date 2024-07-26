package main

import (
	"github.com/donnie4w/daobuilder/util"
	"testing"
)

func TestDdao(t *testing.T) {
	util.InitDB("jdao")
	fileBuilder()
}
