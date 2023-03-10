package test2048;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

class Mypanel extends JPanel implements ActionListener{
	
	JButton up = new JButton();
	JButton down = new JButton();
	JButton right = new JButton();
	JButton left = new JButton();
	JButton reset = new JButton();
	// ������� ��ġ Ű
	
	JButton score = new JButton(); // ��� ������
	
	int size = 4;
	JButton bottons[][] = new JButton[size][size]; // �� 4X4
	int data[][] = new int[size][size];
	Random ran;
	int num = 0; // score
	
	Mypanel(){
		ran = new Random();
		this.setLayout(null); // �̰ɷ� �⺻ ���̾ƿ� ����
		score.setText("SCORE : "+num);
		score.setBounds(700, 100, 150, 80);  // ��ġ�� ũ��
		score.setBackground(new Color(199,239,223)); // ���� ����
		add(score);
		
		up.setText("��");
		up.setBounds(800, 400, 60, 60);
		up.addActionListener(this);
		add(up);
		
		reset.setText("����");
		reset.setBounds(800, 460, 60, 60);
		reset.setBackground(Color.WHITE);
		reset.addActionListener(this);
		add(reset);
		
		right.setText("��");
		right.setBounds(860, 460, 60, 60);
		right.addActionListener(this);
		add(right);
		
		left.setText("��");
		left.setBounds(740, 460, 60, 60);
		left.addActionListener(this);
		add(left);
		
		down.setText("��");
		down.setBounds(800,520,60,60);
		down.addActionListener(this);
		add(down);
		
		for(int i=0;i<size;i++) {
			for(int n=0;n<size;n++) {
				bottons[i][n] = new JButton();
				bottons[i][n].setBounds(100+n*120, 100+i*120, 120, 120);
				bottons[i][n].setBackground(new Color(199,239,223));
				add(bottons[i][n]);
				
			}
		}
		RanNum();
		RanNum();
	}
	
	void Reset() {
		for(int i=0;i<size;i++) {
			for(int n=0;n<size;n++) {
				bottons[i][n].setText("");
				data[i][n] = 0;
			}
		}
		num = 0;
		score.setText("SCORE : "+num);
		RanNum();
		RanNum();
	}
	
	void RanNum() {
		for(int i=0;i<100;i++) {
			int r = ran.nextInt(size);
			int r2 = ran.nextInt(size);
			int r3 = ran.nextInt(10); // 10���� �� �� 4�� ����
			if(data[r][r2] != 0) { // �̹� ä���� ������ ���� �Ұ���
				continue;
			}
			
			if(r3 == 1) {
				bottons[r][r2].setText("4");
				data[r][r2] = 4;
				System.out.println("���� : "+r+","+r2);
				break;
			}else {
				bottons[r][r2].setText("2");
				data[r][r2] = 2;
				System.out.println("���� : "+r+","+r2);
				break;
			}
		}
	}
	
	void up(int y, int x) { // up �϶� �ڸ� ��ü
		for(int i=y-1;i>=0;i--) {
			if(data[i][x] != 0) {
				System.out.println("up!=0");
				int temp = data[y][x];
				data[y][x] = data[i+1][x];
				data[i+1][x] = temp;
				break;
			}
			if(i==0 && data[i][x] == 0) {
				System.out.println("up=0");
				int temp = data[y][x];
				data[y][x] = data[i][x];
				data[i][x] = temp;
			}
		}
	}
	
	void down(int y, int x) { // down �϶� �ڸ� ��ü
		for(int i=y+1;i<size;i++) {
			if(data[i][x] != 0) {
				System.out.println("down!=0");
				int temp = data[y][x];
				data[y][x] = data[i-1][x];
				data[i-1][x] = temp;
				break;
			}
			if(i == size-1 && data[i][x] == 0) {
				System.out.println("down=0");
				int temp = data[y][x];
				data[y][x] = data[i][x];
				data[i][x] = temp;
			}
		}
	}
	
	void right(int y, int x) { // right �϶� �ڸ� ��ü
		for(int n=x+1;n<size;n++) {
			if(data[y][n] != 0) {
				System.out.println("right!=0");
				int temp = data[y][x]; // �����ڸ� �� ����
				data[y][x] = data[y][n-1]; // �����ڸ��� 0�� �ƴ� ���� �ϳ� �� �κа� ��ü
				data[y][n-1] = temp;
				break;
			}
			if(n == size-1 && data[y][n] == 0) {
				System.out.println("right=0");
				int temp = data[y][x];
				data[y][x] = data[y][n];
				data[y][n] = temp;
			}
		}
	}
	
	void left(int y, int x) {  // left �϶� �ڸ� ��ü
		for(int n=x-1;n>=0;n--) {
			if(data[y][n] != 0) {
				System.out.println("left!=0");
				int temp = data[y][x];
				data[y][x] = data[y][n+1];
				data[y][n+1] = temp;
				break;
			}
			if(n == 0 && data[y][n] == 0) {
				System.out.println("left=0");
				int temp = data[y][x];
				data[y][x] = data[y][n];
				data[y][n] = temp;
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == reset) { // ���¹�ư ���� ���
			Reset();
			return;
		}
		
		//-----------------------------------------------
		if(e.getSource() == up) { // up ��ư
			for(int i=0;i<size;i++) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("up : "+i+","+n);
						up(i,n);
					}
				}
			}
			
			for(int n=0;n<size;n++) {
				for(int i=0;i<3;i++) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i+1][n]) { // 0�� �ƴϸ鼭 ���Ʒ� ������ ���
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i+1][n] = 0;
						for(int k=size-1;k>i+1;k--) {
							if(data[k][n] == 0) {
								continue;
							}
							int temp = data[k][n];
							data[k][n] = data[k-1][n];
							data[k-1][n] = temp;
						}
					}
				}
			}
		}
		//-------------------------------------
		else if(e.getSource() == down) { // down ��ư
			for(int i=size-1;i>=0;i--) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("down : "+i+","+n);
						down(i,n);
					}
				}
			}
			
			for(int n=0;n<size;n++) {
				for(int i=size-1;i>0;i--) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i-1][n]) { // 0�� �ƴϸ鼭 �Ʒ��� ����
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i-1][n] = 0;
						for(int k=i-1;k>0;k--) {
							if(data[k][n] == 0) {
								continue;
							}
							int temp = data[k][n];
							data[k][n] = data[k-1][n];
							data[k-1][n] = temp;
						}
					}
				}
			}
		}
		//----------------------------------------------
		else if(e.getSource() == right) { // right ��ư
			for(int i=0;i<size;i++) {
				for(int n=size-1;n>=0;n--) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("right : "+i+","+n);
						right(i,n);
					}
				}
			}
			for(int i=0;i<size;i++) {
				for(int n=size-1;n>0;n--) {
					if(data[i][n] == 0) { // ��ĭ�̸� �Ѿ��
						continue;
					}
					if(data[i][n] == data[i][n-1]) { // 0�� �ƴϸ鼭 ������ ���� ����
						data[i][n] = data[i][n]*2;
						num+=data[i][n]; // �߰� ����
						data[i][n-1] = 0;
						for(int k=n-1;k>0;k--) {
							if(data[i][k] == 0) {
								continue;
							}
							int temp = data[i][k];
							data[i][k] = data[i][k-1];
							data[i][k-1] = temp;
						}
					}
				}
			}
			
		}
		//--------------------------------------------
		else if(e.getSource() == left) { // left ��ư
			for(int i=0;i<size;i++) {
				for(int n=0;n<size;n++) {
					if(data[i][n] != 0) {
						System.out.println("***************");
						System.out.println("left : "+i+","+n);
						left(i,n);
					}
				}
			}
			for(int i=0;i<size;i++) {
				for(int n=0;n<size-1;n++) {
					if(data[i][n] == 0) {
						continue;
					}
					if(data[i][n] == data[i][n+1]) { // 0�� �ƴϸ鼭 ���� ������ ����
						data[i][n] = data[i][n]*2;
						num+=data[i][n];
						data[i][n+1] = 0;
						for(int k=size-1;k>n+1;k--) {
							if(data[i][k] == 0) {
								continue;
							}
							int temp = data[i][k];
							data[i][k] = data[i][k-1];
							data[i][k-1] = temp;
						}
					}
				}
			}
		}
		//--------------------------------------
		RanNum();
		score.setText("SCORE : "+num);
		
		for(int i=0;i<size;i++) {  // �ǿ� ǥ��
			for(int n=0;n<size;n++) {
				if(data[i][n] == 0) {
					bottons[i][n].setText("");
				}else {
					bottons[i][n].setText(Integer.toString(data[i][n]));
				}
			}
		}
	}
	
}

public class Test2048 {
	public static void main(String[] args) {
		
		JFrame frame = new JFrame();
		frame.setTitle("2048"); // frame�� ����
		frame.setBounds(0,0,1000,800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); // �ð�ȭ
		Mypanel mp = new Mypanel();
		frame.setContentPane(mp); // frame �� Mypanel�� ����
		frame.revalidate();  // ���ΰ�ħ ��Ȱ

	}

}
