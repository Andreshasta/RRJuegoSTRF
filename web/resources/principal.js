/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var juego=new Phaser.Game(480,350, Phaser.Auto, 'Juego');
var estadoPrincipal={
    preload: function(){
      //carga todos los recursos  
      Juego.stage.backgrounColor="#000"
    },
    create: function() {
        //mostarar en pantalla recursos cargados
        },
    update: function() {
        //animamos el juego
        }
    
};
Juego.state.add('principal', estadoPrincipal);
juego.state.start('principal');