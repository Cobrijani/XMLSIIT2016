/**
 * Created by Arsa on 7/3/2017.
 */
(function () {
  'use strict';

  angular
    .module('app')
    .component('amandmanCreateComponent', {
      controller: AmandmanCreateController,
      controllerAs: 'vm',
      templateUrl: 'app/home/amandman/amandman-create.component.html',
      bindings: {}
    });

  AmandmanCreateController.$inject = ['$scope', '$stateParams', 'GenericResource', 'exception', '$state', 'AmandmanSpecification', 'AmandmanIdsIncrementerService'];

  function AmandmanCreateController($scope, $stateParams, GenericResource, exception, $state, AmandmanSpecification, AmandmanIdsIncrementerService) {
    var vm = this;
    vm.enableXonomy = false;
    vm.validation = {
      message: ''
    };
    activate();
    vm.createAmandman = createAmandman;
    vm.radioBtnChange = radioBtnChange;
    vm.docMode = 'laic';
    radioBtnChange(vm.docMode);
    vm.spec = AmandmanSpecification.amandman;

    //content

    function activate() {
      vm.aktId = $stateParams.aktId;

      GenericResource.getEntities('akti/'+ vm.aktId)
        .then(function (success) {
          vm.akt = success;
          AmandmanSpecification.setAktReferences(getAktReferences(vm.akt));
          vm.enableXonomy = true;
        })
        .catch(function (error) {
          exception.catcher(error);
        });


      vm.amandman = "<am:amandman xmlns:am='http://parlament.gov.rs/rs.ac.uns.ftn.model.amandman'" +
        " xmlns:xsi = 'http://www.w3.org/2001/XMLSchema-instance'" +
        " xmlns:meta = 'http://parlament.gov.rs/rs.ac.uns.ftn.model.metadata'" +
        " xmlns:rdf = 'http://www.w3.org/1999/02/22-rdf-syntax-ns#'" +
        " xmlns:rdfa = 'http://www.w3.org/ns/rdfa#' " +
        " xmlns:akt = 'http://parlament.gov.rs/rs.ac.uns.ftn.model.akt'" +
        " xmlns:document = 'http://parlament.gov.rs/rs.ac.uns.ftn.model.document'" +
        " akt_id = '" + vm.aktId + "'>" +
        "<am:zaglavlje_amandman><am:akt_ref/><meta:naziv datatype='xs:string' property='pred:imeDokumenta'/></am:zaglavlje_amandman>" +
        "<am:izmene></am:izmene>" +
        "<am:document_am_ref>"+
          "<document:document>"+
            "<document:state>default</document:state>"+
            "<document:result>default</document:result>"+
            "<document:results for='0' against='0' notVote='0'/>"+
          "</document:document>"+
        "</am:document_am_ref>"+
      "</am:amandman>";
      $scope.$on('validation:amandman', function (scope, data) {
        vm.validation = data;
      });

    }

    function radioBtnChange(val) {
      if (val === 'raw') {
        vm.xonomyMode = false;
        vm.amandman = Xonomy.harvest();
      } else {
        vm.xonomyMode = true;
      }
    }

    function createAmandman() {
      GenericResource.postEntity('amandmani', Xonomy.harvest(), {
        'Content-Type': 'application/xml'
      })
        .then(function () {
          $state.go('main');
        })
        .catch(function (error) {
          exception.catcher(error);
        });
    }

    function getAktReferences(akt){
      var akt_references = {
        clanovi: [],
        stavovi: [],
        tacke: [],
        podtacke: [],
        alineje: []
      };

      var clanovi = [];
      var stavovi = [];
      var tacke = [];
      var podtacke = [];
      var alineje = [];


      function getClanoviFromStructure(struct){
        var clanovi =[];
        //ako se glava sastoji od clanova
        if(struct.clan.length>0) {
          for (var i = 0; i < struct.clan.length; i++) {
            var clan = struct.clan[i];
            clanovi.push(clan);
          }
        }else{
          for (var i = 0; i < struct.odeljak.length; i++) {
            //ako glava ima clanove u odeljku
            var odeljak = struct.odeljak[i];
            if (odeljak.clan.length > 0) {
              for (var j = 0; j < odeljak.clan.length; j++) {
                var clan = odeljak.clan[j];
                clanovi.push(clan);
              }
            } else {  // ako glava ima clanove u pododeljku
              for (var j = 0; j < odeljak.pododeljak.length; j++) {
                var pododeljak = odeljak.pododeljak[j];
                for (var k = 0; k < pododeljak.clan.length; k++) {
                  var clan = pododeljak.clan[k];
                  clanovi.push(clan);
                }
              }
            }
          }
        }
        return clanovi;
      }

      //dobavljanje svih clanova
      if (akt.deo.length > 0){
        for (var i = 0; i < akt.deo.length; i++){
          var delovi = akt.deo;
          for (var j = 0; j < delovi[i].glava.length; j++){
            var glave = delovi[i].glava;
            clanovi = clanovi.concat(getClanoviFromStructure(glave[j]));

          }
        }
      }else if (akt.glava.length > 0){
        for (var j = 0; j < akt.glava.length; j++){
          var glave = akt.glava;
          clanovi = clanovi.concat(getClanoviFromStructure(glave[j]));
        }
      }else{
        clanovi = clanovi.concat(getClanoviFromStructure(akt));
      }


      //izdvoj iz clanova id i naziv
      for (var i = 0; i < clanovi.length; i++){
        akt_references.clanovi.push({value: clanovi[i].id, caption: clanovi[i].naziv});
      }

      //izdvoj iz stavova id i deo contenta
      for (var i = 0; i < clanovi.length; i++){
        stavovi = stavovi.concat(clanovi[i].stav);
        for (var j = 0; j < clanovi[i].stav.length; j++){
          var content = clanovi[i].stav[j].content[0];
          if(content===undefined){
            content = "";
          }else{
            content = content.trim().substring(0,30)+"...";
          }
          akt_references.stavovi.push({value: clanovi[i].stav[j].id, caption: content});
        }
      }


      //izdvoj iz tacaka id i naziv
      var tacke = [];
      for (var i = 0; i < stavovi.length; i++){
        for(var j = 0; j < stavovi[i].content.length; j++){
          if((stavovi[i].content[j]).hasOwnProperty("content")){ //ako nema content onda je referenca
            var tacka = stavovi[i].content[j];
            tacke.push(tacka);
            akt_references.tacke.push({value: tacka.id, caption: tacka.naziv});
          }
        }
      }

      //izdvoj iz podtacaka id i naziv
      var podtacke = [];
      for (var i = 0; i < tacke.length; i++){
        for(var j = 0; j < tacke[i].content.length; j++){
          if((tacke[i].content[j]).hasOwnProperty("content")){ //ako nema content onda je referenca
            var podtacka = tacke[i].content[j];
            podtacke.push(podtacka);
            akt_references.podtacke.push({value: podtacka.id, caption: podtacka.naziv});
          }
        }
      }

      //izdvoj iz alineja id i naziv
      var alineje = [];
      for (var i = 0; i < podtacke.length; i++){
        for(var j = 0; j < podtacke[i].content.length; j++){
          if((podtacke[i].content[j]).hasOwnProperty("content")){ //ako nema content onda je referenca
            var alineja = podtacke[i].content[j];
            alineje.push(alineja);
            var content = alineja.content[0];
            if(content===undefined){
              content = "";
            }else{
              content = content.trim().substring(0,30)+"...";
            }
            akt_references.alineje.push({value: alineja.id, caption: content});
          }
        }
      }

      return akt_references;
    }
  }
})();
