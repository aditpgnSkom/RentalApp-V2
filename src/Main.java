import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Pelanggan {
  private String nama;
  public Pelanggan(String nama) {
    this.nama = nama;
  }
  public String getNama() {
    return nama;
  }

  @Override
  public String toString() {
    return nama;
  }
}

class Pegawai {
  private String nama;
  public Pegawai(String nama) {
    this.nama = nama;
  }
  public String getNama() {
    return nama;
  }
  @Override
  public String toString() {
    return nama;
  }
}

class PS {
  private String kodePS;
  private String tipePS;
  private double hargaSewa;
  public PS(String kodePS, String tipePS, double hargaSewa) {
    this.kodePS = kodePS;
    this.tipePS = tipePS;
    this.hargaSewa = hargaSewa;
  }
  public String getKodePS() {
    return kodePS;
  }
  public String getTipePS() {
    return tipePS;
  }
  public double getHargaSewa() {
    return hargaSewa;
  }
  @Override
  public String toString() {
    return tipePS;
  }
}

class Sewa {
  private Pelanggan pelanggan;
  private Pegawai pegawai;
  private PS ps;
  private String tanggalSewa;
  private String tanggalKembali;
  private double durasi;
  public Sewa(Pelanggan pelanggan, Pegawai pegawai, PS ps, String tanggalSewa, String tanggalKembali, double durasi) {
    this.pelanggan = pelanggan;
    this.pegawai = pegawai;
    this.ps = ps;
    this.tanggalSewa = tanggalSewa;
    this.tanggalKembali = tanggalKembali;
    this.durasi = durasi;
  }
  public Pelanggan getPelanggan() {
    return pelanggan;
  }
  public Pegawai getPegawai() {
    return pegawai;
  }
  public PS getPs() {
    return ps;
  }
  public String getTanggalSewa() {
    return tanggalSewa;
  }
  public String getTanggalKembali() {
    return tanggalKembali;
  }
  public double getDurasi() {
    return durasi;
  }
  public double getTotalBiaya() {
    return ps.getHargaSewa() * durasi;
  }
  @Override
  public String toString() {
    return "Nota:\n" +
            "Pelanggan: " + pelanggan.toString() + "\n" +
            "Admin: " + pegawai.toString() + "\n" +
            "Tipe PS: " + ps.toString() + "\n" +
            "Tanggal Sewa: " + tanggalSewa + "\n" +
            "Tanggal Kembali: " + tanggalKembali + "\n" +
            "Lama Sewa(hari): " + durasi + " hari\n" +
            "Total Harga: RP. " + getTotalBiaya();
  }
}

public class Main extends JFrame {
  private JPanel mainPanel;
  private JPanel riwayatPanel;
  private JPanel inputPanel;
  private JTextField inputNamaPelanggan;
  private JTextField inputNamaAdmin;
  private JComboBox inputKodePS;
  private JComboBox inputTipePS;
  private JTextField inputHargaSewa;
  private JTextField inputTanggalSewa;
  private JTextField inputTanggalKembali;
  private JTextField inputDurasiSewa;
  private JLabel namaPelangganLabel;
  private JLabel namaAdminLabel;
  private JLabel kodePSLabel;
  private JLabel tipePSLabel;
  private JLabel hargaSewaLabel;
  private JLabel tanggalSewaLabel;
  private JLabel tanggalKembaliLabel;
  private JLabel durasiSewaLabel;
  private JButton cetakBtn;
  private JList riwayatList;
  private DefaultListModel<Sewa> sewaListModel = new DefaultListModel<>();

  public Main() {
    setContentPane(mainPanel);
    setTitle("APLIKASI RENTAL PS");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(800,600);
    setLocationRelativeTo(null);
    setVisible(true);
    setFont(new Font("Poppins", Font.PLAIN, 14));

    //membuat value inputKodePS dan inputTipePS

    inputKodePS.addItem("Kode-1");
    inputKodePS.addItem("Kode-2");
    inputKodePS.addItem("Kode-3");
    inputKodePS.addItem("Kode-4");
    inputTipePS.addItem("PlayStation 1");
    inputTipePS.addItem("PlayStation 2");
    inputTipePS.addItem("PlayStation 3");
    inputTipePS.addItem("PlayStation 4");

    //input panel
    riwayatList.setModel(sewaListModel);
    cetakBtn.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        Pelanggan pelanggan = new Pelanggan(inputNamaPelanggan.getText());
        Pegawai pegawai = new Pegawai(inputNamaAdmin.getText());
        PS ps = new PS((String)inputKodePS.getSelectedItem(), (String)inputTipePS.getSelectedItem(), Double.parseDouble(inputHargaSewa.getText()));
        if (pelanggan != null && pegawai != null && ps != null) {
          try {
            Sewa sewa = new Sewa(pelanggan, pegawai, ps, inputTanggalSewa.getText(), inputTanggalKembali.getText(), Double.parseDouble(inputDurasiSewa.getText()));
            sewaListModel.addElement(sewa);
            JOptionPane.showMessageDialog(mainPanel, sewa);
          } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(mainPanel, "Isi data dengan lengkap.");
          }
        }
        //kosongkan text setelah cetak
        inputNamaPelanggan.setText("");
        inputNamaAdmin.setText("");
        inputKodePS.setSelectedItem("Kode-1");
        inputTipePS.setSelectedItem("PlayStation 1");
        inputHargaSewa.setText("");
        inputTanggalSewa.setText("");
        inputTanggalKembali.setText("");
        inputDurasiSewa.setText("");
      }
    });
  }

  public static void main(String[] args) {
    new Main();
  }
}