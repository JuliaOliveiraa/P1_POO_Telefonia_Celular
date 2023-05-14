package main;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class AssinantePrePago extends Assinante {
	private List<Recarga> recargas;
	private float creditos;

	public AssinantePrePago(String cpf, String nome, String numero) {
		super(cpf, nome, numero);
		this.recargas = new ArrayList<>();
		this.creditos = 0.0f;
	}

	@Override
	public void fazerChamada(GregorianCalendar data, int duracao) {
		float custoChamada = duracao * 1.45f;

		if (custoChamada < creditos) {
			Chamada chamada = new Chamada(data, duracao);
			chamadas.add(chamada);
			creditos -= custoChamada;
			System.out.println("Chamada registrada com sucesso!");
		} else {
			System.out.println("Chamada não pode ser executada, não tem créditos disponíveis para a mesma!");
		}
	}

	public void recarregar(GregorianCalendar data, float valor) {
		Recarga recarga = new Recarga(data, valor);
		recargas.add(recarga);
		creditos += valor;
		System.out.println("Recarga realizada com sucesso para o assinante CPF: " + getCpf());
	}

	public void imprimirFatura(int mes) {
		System.out.println("CPF: " + getCpf());
		System.out.println("Nome: " + getNome());
		System.out.println("Número: " + getNumero());

		// Imprimir chamadas do mês
		System.out.println("Chamadas do mês:");
		for (int i = 0; i < chamadas.size(); i++) {
			Chamada chamada = chamadas.get(i);
			if (chamada.getData().get(GregorianCalendar.MONTH) == mes) {
				System.out.println("Data: " + chamada.getData().getTime());
				System.out.println("Duração: " + chamada.getDuracao() + " minutos");
				float custo = chamada.getDuracao() * 1.45f;
				System.out.println("Custo: R$" + custo);
			}
		}

		// Imprimir recargas do mês
		System.out.println("Recargas do mês:");
		for (int i = 0; i < recargas.size(); i++) {
			Recarga recarga = recargas.get(i);
			if (recarga.getData().get(GregorianCalendar.MONTH) == mes) {
				System.out.println("Data: " + recarga.getData().getTime());
				System.out.println("Valor: R$" + recarga.getValor());
			}
		}

		System.out.println("Total de chamadas: " + chamadas.size());
		System.out.println("Total de recargas: " + recargas.size());
		System.out.println("Créditos disponíveis: R$" + creditos);
	}
}
