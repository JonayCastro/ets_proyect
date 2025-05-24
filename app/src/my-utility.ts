export function capitalize(text: string): string {
  if (!text) {
    return '';
  }
  return text.charAt(0).toUpperCase() + text.slice(1).toLowerCase();
}

export function add(a: number, b: number): number {
  return a + b;
}