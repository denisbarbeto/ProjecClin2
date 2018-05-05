package modeloDao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modeloBeans.BeansAgenda;
import modeloBeans.BeansConsulta;
import modeloConection.ConexaoDB;
import java.sql.Timestamp;


public class DaoConsulta {
    BeansConsulta consulta = new BeansConsulta();
    BeansAgenda agenda = new BeansAgenda();
    
    ConexaoDB conex = new ConexaoDB(); //Conexão Principal
    ConexaoDB conexAgenda = new ConexaoDB(); //Conexão para trazer a Data do Agendamento
    ConexaoDB conexMotivo = new ConexaoDB(); //Conexão para trazer o Motivo da Consulta
   public int Id_Agenda;
   

    
        public void Salvar (BeansConsulta consulta){
        
          // BuscarDataAgenda(agenda.getData()); //Trará os dados da tabela Agenda/Data
          // BuscarMotivo(agenda.getMotivo()); //Trará os dados da tabela Agenda/Motivo
          
          conex.getConnection();
        try {
            PreparedStatement pst = conex.con.prepareStatement
("insert into consulta "
+ "(consulta_diagnostico,consulta_receita, data_hora_ini, data_hora_fin)"
+ "values (?,?,?,?)");
           
           pst.setString(1,consulta.getDiagnostico());
           pst.setString(2,consulta.getReceita());
           pst.setDate(3, new java.sql.Date(consulta.getData_ini().getTime()));
           pst.setTimestamp(4, new java.sql.Timestamp(consulta.getData_fin().getTime()));
           pst.execute();
           JOptionPane.showMessageDialog(null,"Dados da Consulta Salvo com Sucesso");
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Salvar dados na Tabela Consulta" + ex);
        }
         conex.desconecta();
         
}
    /*//Método que Busca a Data da Consulta e Seta ela na Variável dataInicial
    public void BuscarDataAgenda ( Date Data ){
        conexAgenda.getConnection();
        conexAgenda.executaSql(" select * from agenda where agenda_data '" +Data+ "'");
        try {
            conexAgenda.rs.first();
            dataInicial= conexAgenda.rs.getDate("agenda_data");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Data" + ex);
        }
        
        }*/
    
   /* //Método que Busca o Motivo da Consulta e seta ele na Variável motivoConsulta
    public void BuscarMotivo (String Motivo){
        conexMotivo.getConnection();
        conexMotivo.executaSql(" select * from agenda where motivo '" +Motivo+ "'");
        try {
            conexMotivo.rs.first();
            motivoConsulta= conexAgenda.rs.getInt("id_agenda");
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Erro ao Buscar Motivo da Consulta" + ex);
        }
    }
    */
        
        public int BuscarIdAgenda (int IdAgenda){
            
            conexAgenda.getConnection();
            conexAgenda.executaSql("select * from agenda where id_agenda '"+IdAgenda+"'");
            try {
                conexAgenda.rs.first();
                Id_Agenda = conexAgenda.rs.getInt("id_agenda") ;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Erro ao Buscar Id na Tabela Agenda" + ex);
            }
            
             return Id_Agenda;
        }
}
